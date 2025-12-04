package uk.ac.york.ci.corvus;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.diff.IDiffEngine;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
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

public class CorvusRunner implements IApplication {

	private IProgressMonitor progressMonitor;
//	private final String OUT_PATH = "C:/Users/nr823/eclipse-workspace/CI-corvus-2/empty/";
//	private final String OS_PATH = "C:/Users/nr823/eclipse-workspace/CI-corvus-2/psl.example.versions/";
	private final String OS_PATH = "/example/";
	private final String OUT_PATH= "/output/";

	@Override
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		System.out.println(context.getArguments());
		Map<String, Object> contextArguments = context.getArguments();
		progressMonitor = new NullProgressMonitor();
		return run(contextArguments.get(IApplicationContext.APPLICATION_ARGS));
	}

	private Object run(Object argsArray) {
		 // Get session from an absolute path (not in a workspace)
		
		URI oldSessionResourceURI = URI.createFileURI(OS_PATH + "old/acme.aird");
		URI newSessionResourceURI = URI.createFileURI(OS_PATH + "new/acme.aird");
		URI oldSemanticResourceURI = URI.createFileURI(OS_PATH + "old/acme.psl");
		URI newSemanticResourceURI = URI.createFileURI(OS_PATH + "new/acme.psl");
		Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
        
        
		try {
			
			new File(OUT_PATH+"model/old/").mkdirs();
			new File(OUT_PATH+"model/new/").mkdirs();
			
			DefaultLocalSessionCreationOperation oldCreation = new DefaultLocalSessionCreationOperation(oldSessionResourceURI, progressMonitor);
			oldCreation.execute();
			Session oldSession = oldCreation.getCreatedSession();
			
			DefaultLocalSessionCreationOperation newCreation = new DefaultLocalSessionCreationOperation(newSessionResourceURI, progressMonitor);
			newCreation.execute();
			Session newSession = newCreation.getCreatedSession();
			
			addSemanticResources(newSession, newSemanticResourceURI);
			addSemanticResources(oldSession, oldSemanticResourceURI);
			
			ResourceSet rsNew = newSession.getSemanticResources().iterator().next().getResourceSet();
			ResourceSet rsOld = oldSession.getSemanticResources().iterator().next().getResourceSet();
			
			addViewpoints(newSession, viewpoints);
			addViewpoints(oldSession, viewpoints);
			
			Comparison comparison = compare(rsOld, rsNew);
			ResourceSet rsComparison = new ResourceSetImpl();
			Resource rComparison = rsComparison.createResource(URI.createFileURI(OS_PATH + "psl.compare"));		
			rComparison.getContents().add(comparison);
			
			
			DialectUIManager duim = DialectUIManager.INSTANCE;
			DialectManager dm = DialectManager.INSTANCE;
			duim.enableDialectUI(new DiagramDialectUI());
			dm.enableDialect(new DiagramDialect());
			oldSession.open(progressMonitor);
			DView newView = newSession.getSelectedViews().iterator().next();
			DView oldView = oldSession.getSelectedViews().iterator().next();
			HashMap<String, RepresentationDescription> oldRepMap = new HashMap<String, RepresentationDescription>();
			for (RepresentationDescription r : oldSession.getSelectedViewpoints(false).iterator().next().getOwnedRepresentations()) {
				oldRepMap.put(r.getName(), r);
			}
			
			for (DRepresentationDescriptor descriptor : newView.getOwnedRepresentationDescriptors()) {
				EObject eObject = comparison.getMatch(descriptor.getTarget()).getLeft();
				RepresentationDescription rd = oldRepMap.get(descriptor.getDescription().getName());
				if (!containsRep(eObject, rd, oldView)) {
					createFormattedRep(oldSession, eObject, rd, duim, dm);
				}
				
			}
			HashMap<String, RepresentationDescription> newRepMap = new HashMap<String, RepresentationDescription>();
			for (RepresentationDescription r : newSession.getSelectedViewpoints(false).iterator().next().getOwnedRepresentations()) {
				newRepMap.put(r.getName(), r);
			}
			
			for (DRepresentationDescriptor descriptor : oldView.getOwnedRepresentationDescriptors()) {
				EObject eObject = comparison.getMatch(descriptor.getTarget()).getRight();
				RepresentationDescription rd = newRepMap.get(descriptor.getDescription().getName());
				if (!containsRep(eObject, rd, newView)) {
					createFormattedRep(newSession, eObject, rd, duim, dm);
				}
			}
			
			for (DRepresentationDescriptor descriptor : oldView.getOwnedRepresentationDescriptors()) {
				exportRep("model/old/old-" + getFileName(descriptor) + ".png", descriptor.getRepresentation(), oldSession, duim);
			}
			
			for (DRepresentationDescriptor descriptor : newView.getOwnedRepresentationDescriptors()) {
				exportRep("model/new/new-" + getFileName(descriptor) + ".png", descriptor.getRepresentation(), newSession, duim);
			}
			
	        File mdFile = new File(OUT_PATH + "plain-sample.md");
	        mdFile.createNewFile();
	        FileWriter mdWriter = new FileWriter(mdFile);
	        
	        mdWriter.write("# Paired up \n");
	        
	        EList<DRepresentationDescriptor> newDescriptors = newView.getOwnedRepresentationDescriptors();
	        for (DRepresentationDescriptor oldDescriptor : oldView.getOwnedRepresentationDescriptors()) {
	        	mdWriter.write("<img src=\"https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/model/old/old-"+ getFileName(oldDescriptor) + ".png?\" width=\"50%\">");
	        	
	        	for (DRepresentationDescriptor newDescriptor : oldView.getOwnedRepresentationDescriptors()) {
	        		if (oldDescriptor.getDescription().getName() == newDescriptor.getDescription().getName() 
	        				&& comparison.getMatch(oldDescriptor.getTarget()) == comparison.getMatch(newDescriptor.getTarget()))  {
	        			mdWriter.write("<img src=\"https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/model/new/new-"+ getFileName(newDescriptor) + ".png?\" width=\"50%\">");
	    	        	break;
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
	
	private boolean containsRep(EObject eObject, RepresentationDescription rd, DView view) {
		for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
			if (descriptor.getTarget().equals(eObject) && descriptor.getDescription().equals(rd)) {
				return true;
			}
		}
		return false;
	}

	private void createFormattedRep(Session session, EObject eObject, RepresentationDescription rd, DialectUIManager dialectUIManager, DialectManager dialectManager) {
		session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
			   @Override
			   protected void doExecute() {
				   DRepresentation representation = dialectManager.createRepresentation("Test", eObject, rd, session, progressMonitor);
				   Diagram diagram = SiriusGMFHelper.getGmfDiagram((DDiagram) representation);
				   DiagramEditPart editPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, new Shell());
				   editPart.enableEditMode();
				   List<EditPart> editParts = new ArrayList<EditPart>();
				   editParts.add(editPart);

				   ArrangeRequest request = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL, LayoutType.DEFAULT);
				   request.setPartsToArrange(editParts);
				   editPart.performRequest(request);
				   
				   ArrangeRequest request2 = new ArrangeRequest(ActionIds.ACTION_AUTOSIZE, LayoutType.DEFAULT);
				   request2.setPartsToArrange(editParts);
				   editPart.performRequest(request2);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void completeCreate(URI semanticResourceURI, Session session, DialectUIManager dialectUIManager, DialectManager dialectManager,
			Set<Viewpoint> viewpoints, String path) {
		session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
			   @Override
			   protected void doExecute() {
				   session.addSemanticResource(semanticResourceURI, progressMonitor);
				   new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), viewpoints, new HashSet<Viewpoint>(), progressMonitor).execute();
				   Collection<Resource> resources = session.getSemanticResources();
				   EObject eObject = ((Resource) resources.toArray()[0]).getAllContents().next();
				   RepresentationDescription rd = session.getSelectedViewpoints(false).iterator().next().getOwnedRepresentations().get(2);
				   DRepresentation representation = dialectManager.createRepresentation("Test", eObject, rd, session, progressMonitor);
				   DView dView = session.getOwnedViews().iterator().next();
				   System.out.println(session.getOwnedViews().iterator().next());
				   Diagram diagram = SiriusGMFHelper.getGmfDiagram((DDiagram) representation);
				   DiagramEditPart editPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, new Shell());
				   editPart.enableEditMode();
				   List<EditPart> editParts = new ArrayList<EditPart>();
				   editParts.add(editPart);

				   ArrangeRequest request = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL, LayoutType.DEFAULT);
				   request.setPartsToArrange(editParts);
				   editPart.performRequest(request);
				   
				   ArrangeRequest request2 = new ArrangeRequest(ActionIds.ACTION_AUTOSIZE, LayoutType.DEFAULT);
				   request2.setPartsToArrange(editParts);
				   editPart.performRequest(request2);
				   ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
			       Path exportPath = new Path(OS_PATH + path);
				   try {
					dialectUIManager.export(representation, session, exportPath, exportFormat,
					    		new NullProgressMonitor());
				} catch (SizeTooLargeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }   
		   });
	}
	
	private static Comparison compare(ResourceSet rsLeft, ResourceSet rsRight) {
		MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl(UseIdentifiers.WHEN_AVAILABLE);
		MatchEngineFactoryRegistryImpl matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		IDiffProcessor diffProcessor = new DiffBuilder();
		IDiffEngine diffEngine = new DefaultDiffEngine(diffProcessor) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return new FeatureFilter() {
					@Override
					public boolean checkForOrderingChanges(EStructuralFeature feature) {
						return false;
					}
				};
			}
		};
		

		EMFCompare emfCompare = EMFCompare.builder()
			.setMatchEngineFactoryRegistry(matchEngineRegistry)
			.setDiffEngine(diffEngine)
			.build();

		EcoreUtil.resolveAll(rsLeft);
		EcoreUtil.resolveAll(rsRight);
		
		DefaultComparisonScope scope = new DefaultComparisonScope(rsLeft, rsRight, null);
		Comparison comparison = emfCompare.compare(scope);
		return comparison;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
