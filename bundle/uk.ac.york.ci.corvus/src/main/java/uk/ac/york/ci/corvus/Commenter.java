package uk.ac.york.ci.corvus;

import java.io.IOException;

import org.kohsuke.github.*;

public class Commenter {
	public static void main(String[] args) throws IOException {
		GitHub github = GitHub.connectUsingOAuth("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");

		GHPullRequest pr = github.getRepository("nwxrichardson/psl-ci").getPullRequest(4);
		pr.comment("Testing Java API");
	}
}

