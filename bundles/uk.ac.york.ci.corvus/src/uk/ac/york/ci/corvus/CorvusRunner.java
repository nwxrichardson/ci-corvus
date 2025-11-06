package uk.ac.york.ci.corvus;

import java.io.File;
import java.util.Map;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public class CorvusRunner implements IApplication {

	private IProgressMonitor progressMonitor;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		Map<String, Object> contextArguments = context.getArguments();
		return run(contextArguments.get(IApplicationContext.APPLICATION_ARGS));
	}

	private Object run(Object argsArray) {
		 // Get session from an absolute path (not in a workspace)
        URI sessionResourceURI = URI.createFileURI("C:/Users/nr823/git/CI-Corvus/bundles/uk.ac.york.ci.corvus/acme.aird");
        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
		try {
			
			session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
			session.open(new NullProgressMonitor());
	        DViewQuery query = new DViewQuery(session.getOwnedViews().iterator().next());
	        DRepresentationDescriptor representationDesc = query.getLoadedRepresentationsDescriptors().get(0);
	        DRepresentation representation = representationDesc.getRepresentation();
//	         Export it as SVG image
	        ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
	        ExportResult exp = DialectUIManager.INSTANCE.exportWithResult(representation, session, new Path("C:/Users/nr823/git/CI-Corvus/bundles/uk.ac.york.ci.corvus/image.png"), exportFormat,
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
