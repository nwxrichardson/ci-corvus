package uk.ac.york.test.ci.corvus.comment;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;

import org.kohsuke.github.api;

public class CommenterJob extends Job {
	public CommenterJob(String name) {
		super(name);
	}

	public static void main(String[] args) throws CoreException {
		CommenterJob job = new CommenterJob("main test");
		job.setUser(true);
		job.schedule();
	}

	public void outputImage() {
		System.out.println(SessionManager.INSTANCE.getSessions());
		URI sessionResourceURI = URI.createPlatformResourceURI("/Project/archi.aird", true);
		Session createdSession = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
		createdSession.open(new NullProgressMonitor());
		
		for (DRepresentation rep: DialectManager.INSTANCE.getAllRepresentations(createdSession)) {
			ExportFormat exp = new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG);
			DialectUIManager.INSTANCE.canExport(rep, exp);
		}
//		return Status.OK_STATUS;
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		GitHub github = GitHub.connect();

		GHRepository repo = github.createRepository(
		  "new-repository","this is my new repository",
		  "https://www.kohsuke.org/",true/*public*/);
		repo.addCollaborators(github.getUser("abayer"),github.getUser("rtyler"));
		repo.delete();
	}
}
