package uk.ac.york.ci.corvus;

import java.util.Map;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUI;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public class CorvusRunner implements IApplication {

	private IProgressMonitor progressMonitor;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		System.out.println(context.getArguments());
		Map<String, Object> contextArguments = context.getArguments();
		return run(contextArguments.get(IApplicationContext.APPLICATION_ARGS));
	}

	private Object run(Object argsArray) {
		 // Get session from an absolute path (not in a workspace)
		URI sessionResourceURI;
//		sessionResourceURI = URI.createFileURI("C:\\Users\\nr823\\git\\CI-Corvus\\bundles\\uk.ac.york.ci.corvus\\acme.aird");
		sessionResourceURI = URI.createFileURI("/example/acme.aird");
        
        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
		try {
			DialectUIManager dm = DialectUIManager.INSTANCE;
			dm.enableDialectUI(new DiagramDialectUI());
			session = SessionFactory.INSTANCE.createDefaultSession(sessionResourceURI);
			session.open(new NullProgressMonitor());
	        DViewQuery query = new DViewQuery(session.getOwnedViews().iterator().next());
	        DRepresentationDescriptor representationDesc = query.getLoadedRepresentationsDescriptors().get(0);
	        DRepresentation representation = representationDesc.getRepresentation();
//	         Export it as SVG image
	        ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
	        Path path = new Path("/example/image.png");
//	        Path path = new Path("C:\\Users\\nr823\\git\\CI-Corvus\\bundles\\uk.ac.york.ci.corvus\\acme.png");
	        dm.export(representation, session, path, exportFormat,
	        		new NullProgressMonitor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	@Override
	public void stop() {
		if (progressMonitor != null) {
			progressMonitor.setCanceled(true);
		}
	}

}
