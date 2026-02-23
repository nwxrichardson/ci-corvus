package uk.ac.york.ci.corvus;

import corvusmatch.Comparison;
import corvusmatch.Match;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialect;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUI;
import org.eclipse.sirius.diagram.ui.tools.internal.part.OffscreenEditPartFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Shell;
import uk.ac.york.corvus.jobs.CorvusCompareGen;

public class CorvusRunner implements IApplication {

	private IProgressMonitor progressMonitor;
	private final String OUT_PATH = "C:/Users/nr823/eclipse-workspace/CI-corvus-2/empty/";
	private final String OS_PATH = "C:/Users/nr823/eclipse-workspace/CI-corvus-2/psl.example.versions/";
//	private final String OS_PATH = "/example/";
//	private final String OUT_PATH= "/output/";
	DialectUIManager dialectUIManager = DialectUIManager.INSTANCE;
	DialectManager dialectManager = DialectManager.INSTANCE;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		Map<String, Object> contextArguments = context.getArguments();
		progressMonitor = new NullProgressMonitor();
		dialectUIManager.enableDialectUI(new DiagramDialectUI());
		dialectManager.enableDialect(new DiagramDialect());
		return run(contextArguments.get(IApplicationContext.APPLICATION_ARGS));
	}

	private Object run(Object argsArray) {
		
		HashSet<String> fileExtensions = new HashSet<String>();
		
		// This gets all register file extensions so that the correct model can be used
		for (Object o: Registry.INSTANCE.values()) {
			if (o instanceof EPackage) {
				EPackage ePackage = (EPackage) o;
				fileExtensions.add(ePackage.getName());
			} else if (o instanceof EPackage.Descriptor) {
				EPackage.Descriptor descriptor = (EPackage.Descriptor) o;
				fileExtensions.add(descriptor.getEPackage().getName());
			} 
		}
		
		
		File oldDir = new File(OS_PATH, "old");
		File newDir = new File(OS_PATH, "new");
		File comDir = new File(OS_PATH, "com");
		
		HashSet<String> airdSet = new HashSet<String>();
		airdSet.add("aird");
		
		URI oldSessionResourceURI = sortFileExtension(airdSet, oldDir).iterator().next();
		URI newSessionResourceURI = sortFileExtension(airdSet, newDir).iterator().next();
		URI comSessionResourceURI = URI.createFileURI(OS_PATH + "com/compare.aird");
		Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
		
        
		try {
			
			new File(OUT_PATH+"model/old/").mkdirs();
			new File(OUT_PATH+"model/new/").mkdirs();
			new File(OUT_PATH+"model/com/").mkdirs();
			
			DefaultLocalSessionCreationOperation oldCreation = new DefaultLocalSessionCreationOperation(oldSessionResourceURI, progressMonitor);
			oldCreation.execute();
			Session oldSession = oldCreation.getCreatedSession();
			
			DefaultLocalSessionCreationOperation newCreation = new DefaultLocalSessionCreationOperation(newSessionResourceURI, progressMonitor);
			newCreation.execute();
			Session newSession = newCreation.getCreatedSession();
			
			DefaultLocalSessionCreationOperation comCreation = new DefaultLocalSessionCreationOperation(comSessionResourceURI, progressMonitor);
			comCreation.execute();
			Session comSession = newCreation.getCreatedSession();
			
			addAllModels(fileExtensions, oldSession, oldDir);
			addAllModels(fileExtensions, newSession, newDir);
			addAllModels(fileExtensions, comSession, newDir);
			
			ResourceSet rsNew = newSession.getSemanticResources().iterator().next().getResourceSet();
			ResourceSet rsOld = oldSession.getSemanticResources().iterator().next().getResourceSet();
			rsNew.getResources().iterator().next().setURI(comSessionResourceURI);
			rsNew.getResources().iterator().next().save(null);
			System.out.println(rsNew.getResources().iterator().next());
			
			Resource rComparison = compare(rsOld, rsNew);
			Comparison comparison = (Comparison) rComparison.getContents().iterator().next();
			addSemanticResources(comSession, rComparison.getURI());
			
			addViewpoints(newSession, viewpoints);
			addViewpoints(oldSession, viewpoints);
			addViewpoints(comSession, viewpoints);
			
			oldSession.open(progressMonitor);
			
			HashSet<ImmutablePair<Match, String>> oldDescriptorMap = getDescriptorMap(oldSession, comparison);
			HashSet<ImmutablePair<Match, String>> newDescriptorMap = getDescriptorMap(newSession, comparison);
			
			HashMap<String, RepresentationDescription> oldRepMap = getRepresentationNameMap(oldSession);
			HashMap<String, RepresentationDescription> newRepMap = getRepresentationNameMap(newSession);
			HashMap<String, RepresentationDescription> comRepMap = getRepresentationNameMap(comSession);
			
			HashSet<Pair<Match, String>> oldToCreateMap = new HashSet<>();
			oldToCreateMap.addAll(newDescriptorMap);
			oldToCreateMap.removeAll(oldDescriptorMap);
			
			
			HashSet<Pair<Match, String>> newToCreateMap = new HashSet<>();
			newToCreateMap.addAll(oldDescriptorMap);
			newToCreateMap.removeAll(newDescriptorMap);
			
			HashSet<Pair<Match, String>> comToCreateMap = new HashSet<>();
			comToCreateMap.addAll(oldDescriptorMap);
			comToCreateMap.addAll(newDescriptorMap);
			
			for (Pair<Match, String> toCreate : oldToCreateMap) {
				createFormattedRep(oldSession, toCreate.getKey().getLeft(), oldRepMap.get(toCreate.getValue()));
			}
			
			for (Pair<Match, String> toCreate : newToCreateMap) {
				createFormattedRep(newSession, toCreate.getKey().getRight(), newRepMap.get(toCreate.getValue()));
			}	
			
			for (Pair<Match, String> toCreate : comToCreateMap) {
				createFormattedRep(comSession, toCreate.getKey(), comRepMap.get("Match " + toCreate.getValue()));
			}	
			
			for (DView oldView : oldSession.getOwnedViews()) {
				for (DRepresentationDescriptor descriptor : oldView.getOwnedRepresentationDescriptors()) {
					exportRep("model/old/old-" + getFileName(descriptor) + ".png", descriptor.getRepresentation(), oldSession, dialectUIManager);
				}
			}
			
			for (DView newView : newSession.getOwnedViews()) {
				for (DRepresentationDescriptor descriptor : newView.getOwnedRepresentationDescriptors()) {
					exportRep("model/new/new-" + getFileName(descriptor) + ".png", descriptor.getRepresentation(), newSession, dialectUIManager);
				}
			}
			
			for (DView comView : comSession.getOwnedViews()) {
				for (DRepresentationDescriptor descriptor : comView.getOwnedRepresentationDescriptors()) {
					exportRep("model/com/com-" + getFileName(descriptor) + ".png", descriptor.getRepresentation(), comSession, dialectUIManager);
				}
			}
			
	        File mdFile = new File(OUT_PATH + "plain-sample.md");
	        mdFile.createNewFile();
	        FileWriter mdWriter = new FileWriter(mdFile);
	        
	        mdWriter.write("# Paired up \n");
	        
	        for (DView oldView : oldSession.getOwnedViews()) {
	        	for (DRepresentationDescriptor oldDescriptor : oldView.getOwnedRepresentationDescriptors()) {
	        		for (DView newView : newSession.getOwnedViews()) {
	        			for (DRepresentationDescriptor newDescriptor : newView.getOwnedRepresentationDescriptors()) {
	        				if (oldDescriptor.getDescription().getName().equals(newDescriptor.getDescription().getName()) 
			        				&& ! imageComparison(oldDescriptor, newDescriptor))  {
			        			mdWriter.write("<img src=\"https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/model/old/old-"+ getFileName(oldDescriptor) + ".png?\" width=\"50%\">");
			        			mdWriter.write("<img src=\"https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/model/new/new-"+ getFileName(newDescriptor) + ".png?\" width=\"50%\">");
			        			break;
			        		}
			        	}
	        		}
		        		
				}
	        }
	        
	        
	        mdWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	private HashMap<String, RepresentationDescription> getRepresentationNameMap(Session session) {
		HashMap<String, RepresentationDescription> newRepMap = new HashMap<String, RepresentationDescription>();
		for (Viewpoint view : session.getSelectedViewpoints(false)) {
			for (RepresentationDescription description : view.getOwnedRepresentations()) {
				newRepMap.put(description.getName(), description);
			}
		}
		return newRepMap;
	}

	private HashSet<ImmutablePair<Match, String>> getDescriptorMap(Session session, Comparison comparison) {
		HashSet<ImmutablePair<Match, String>> descriptorMap = new HashSet<ImmutablePair<Match, String>>();
		for (DView view : session.getOwnedViews()) {
			for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
				descriptorMap.add(new ImmutablePair<Match, String>(comparison.getMatch(descriptor.getTarget()), descriptor.getDescription().getName()));
			}
		}
		return descriptorMap;
	}

	private boolean imageComparison(DRepresentationDescriptor oldDescriptor, DRepresentationDescriptor newDescriptor) {
		try {
			File oldFile = new File(OUT_PATH + "model/old/old-" + getFileName(oldDescriptor) + ".png");
			BufferedImage oldImage = ImageIO.read(oldFile);
			DataBuffer oldData = oldImage.getData().getDataBuffer();
			
			File newFile = new File(OUT_PATH + "model/new/new-" + getFileName(newDescriptor) + ".png");
			BufferedImage newImage = ImageIO.read(newFile);
			DataBuffer newData = newImage.getData().getDataBuffer();
			
			if (!(oldImage.getHeight() == newImage.getHeight() && oldImage.getWidth() == newImage.getWidth())) return false; 
			
			for (int i = 0; i < oldData.getSize(); i++) {
				if (!(oldData.getElem(i) == newData.getElem(i))) return false;
			} 
			
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}

	private HashSet<URI> sortFileExtension(HashSet<String> fileExtensions, File dir) {
		HashSet<URI> URIs = new HashSet<>();
		for (File file : dir.listFiles()) {
			String fileName = file.getName();
			int dotIndex = fileName.lastIndexOf(".");
			String fileExtension = (dotIndex > 0) ? fileName.substring(dotIndex + 1) : "";
			if (fileExtensions.contains(fileExtension)) {
				URIs.add(URI.createFileURI(file.getPath()));
			}
		}
		return URIs;
	}
	
	private void addAllModels(HashSet<String> fileExtensions, Session session, File dir) {
		for (URI uri : sortFileExtension(fileExtensions, dir)) {
			addSemanticResources(session, uri);
		}
	}
	
	private String getFileName(DRepresentationDescriptor descriptor) {
			return descriptor.getDescription().getName() + "-"
					+ ((XMIResource)descriptor.getTarget().eResource()).getID(descriptor.getTarget());
		}
	
	private void addSemanticResources(Session session, URI semanticResourceURI) {
		session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
			   @Override
			   protected void doExecute() {
				   session.addSemanticResource(semanticResourceURI, progressMonitor);
			   }
		});
	}
	
	private void addViewpoints(Session session, Set<Viewpoint> viewpoints) {
		session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
			@Override
			protected void doExecute() {
				new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), viewpoints, new HashSet<Viewpoint>(), progressMonitor).execute();
			}
		});
	}

	private void createFormattedRep(Session session, EObject eObject, RepresentationDescription rd) {
		session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
			   @Override
			   protected void doExecute() {
				   DRepresentation representation = dialectManager.createRepresentation("Test", eObject, rd, session, progressMonitor);
				   Diagram diagram = SiriusGMFHelper.getGmfDiagram((DDiagram) representation);
				   DiagramEditPart editPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, new Shell());
				   editPart.enableEditMode();
				   List<DiagramEditPart> editParts = new ArrayList<DiagramEditPart>();
				   editParts.add(editPart);

				   ArrangeRequest request = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL, LayoutType.DEFAULT);
				   request.setPartsToArrange(editParts);
				   editPart.performRequest(request);
				   ArrangeRequest request2 = new ArrangeRequest(ActionIds.ACTION_SELECT_ALL_SHAPES);
				   request.setPartsToArrange(editParts);
				   editPart.performRequest(request2);
				   
				   ArrangeRequest request3 = new ArrangeRequest(ActionIds.ACTION_MAKE_SAME_SIZE_BOTH);
				   request.setPartsToArrange(editParts);
				   editPart.performRequest(request3);
			   }   
		   });
	}
	
	private void exportRep(String path, DRepresentation representation, Session session, DialectUIServices dialectUIManager) {
		   ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
	       Path exportPath = new Path(OUT_PATH + path);
		   try {
			dialectUIManager.export(representation, session, exportPath, exportFormat,
			    		progressMonitor);
		} catch (SizeTooLargeException e) {
			e.printStackTrace();
		}
	}

	private static Resource compare(ResourceSet rsLeft, ResourceSet rsRight) throws Exception {
		CorvusCompareGen corvusGen = new CorvusCompareGen("Generate Corvusmatch", rsLeft, rsRight);
		corvusGen.schedule();
		corvusGen.join();
		return corvusGen.getCorvusMatch();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
