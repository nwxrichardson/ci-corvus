package uk.ac.york.ci.corvus;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialect;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUI;
import org.eclipse.sirius.diagram.ui.tools.internal.part.OffscreenEditPartFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
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
	private final String OS_PATH = "C:/Users/nr823/git/sirius-pr-action-workspace/bundles/psl.example/";
//	private final String OS_PATH = "/example/";

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
		URI sessionResourceURI;
		sessionResourceURI = URI.createFileURI(OS_PATH + "new.aird");
		URI semanticResourceURI = URI.createFileURI(OS_PATH + "acme.psl");
        
        
		try {
			Session session = SessionFactory.INSTANCE.createDefaultSession(sessionResourceURI);
			DialectUIManager duim = DialectUIManager.INSTANCE;
			DialectManager dm = DialectManager.INSTANCE;
			Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
			duim.enableDialectUI(new DiagramDialectUI());
			dm.enableDialect(new DiagramDialect());
			session.open(progressMonitor);
			session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
				   @Override
				   protected void doExecute() {
					   session.addSemanticResource(semanticResourceURI, progressMonitor);
					   new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), viewpoints, new HashSet<Viewpoint>(), progressMonitor).execute();
					   Collection<Resource> resources = session.getSemanticResources();
					   EObject eObject = ((Resource) resources.toArray()[0]).getAllContents().next();
					   RepresentationDescription rd = session.getSelectedViewpoints(false).iterator().next().getOwnedRepresentations().get(1);
					   DRepresentation representation = dm.createRepresentation("Test", eObject, rd, session, progressMonitor);
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
				       Path path = new Path(OS_PATH + "image.png");
					   try {
						duim.export(representation, session, path, exportFormat,
						    		new NullProgressMonitor());
					} catch (SizeTooLargeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }   
			   });

	        
	        
	        File mdFile = new File(OS_PATH + "plain-sample.md");
	        mdFile.createNewFile();
	        FileWriter mdWriter = new FileWriter(mdFile);
	        mdWriter.write("# From Java \n ![image.png](https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/image.png?)");
	        mdWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	public void exportExisting() {
		// Get session from an absolute path (not in a workspace)
				URI sessionResourceURI;
				sessionResourceURI = URI.createFileURI(OS_PATH + "acme.aird");
		        
		        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
				try {
					DialectUIManager dm = DialectUIManager.INSTANCE;
					dm.enableDialectUI(new DiagramDialectUI());
					session = SessionFactory.INSTANCE.createDefaultSession(sessionResourceURI);
					session.open(new NullProgressMonitor());
//					session.createView(null, null, new NullProgressMonitor());
			        DViewQuery query = new DViewQuery(session.getOwnedViews().iterator().next());
			        DRepresentationDescriptor representationDesc = query.getLoadedRepresentationsDescriptors().get(0);
			        DRepresentation representation = representationDesc.getRepresentation();
//			         Export it as SVG image
			        ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
			        Path path = new Path(OS_PATH + "image.png");
			        dm.export(representation, session, path, exportFormat,
			        		new NullProgressMonitor());
			        File mdFile = new File(OS_PATH + "plain-sample.md");
			        mdFile.createNewFile();
			        FileWriter mdWriter = new FileWriter(mdFile);
			        mdWriter.write("# From Java \n ![image.png](https://uk-ac-york-scheme-image-upload-dev.s3.eu-west-1.amazonaws.com/image.png?)");
			        mdWriter.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Override
	public void stop() {
		if (progressMonitor != null) {
			progressMonitor.setCanceled(true);
		}
	}

}
