#!/bin/bash

LAUNCHERJAR=$(ls -1 /epsilon/plugins/org.eclipse.equinox.launcher_*.jar | head -1)
WORKSPACE="/workspace/"

# Run the ant script
xvfb-run java -ea -jar "${LAUNCHERJAR}" \
	-data "${WORKSPACE}" \
	-application uk.ac.york.ci.corvus.CorvusRunner \

# If there was an error, read the Eclipse logs and exit
if [[ $? -gt 0 ]]
then
	cat "${WORKSPACE}/.metadata/.log"
	exit 1
fi

# Otherwise, exit 0, meaning success!
exit 0
