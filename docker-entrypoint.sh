#!/bin/bash

LAUNCHERJAR=$(ls -1 /epsilon/plugins/org.eclipse.equinox.launcher_*.jar | head -1)
WORKSPACE="/workspace/"
ANT_FILE="$1"
ANT_TARGET="$2"

# We might be reading the ant file from stdin
if [[ "${ANT_FILE}" == "--" ]]
then
	ANT_FILE="${WORKSPACE}/build.xml"
	# Use cat to read stdin and write it to file
	cat > "${ANT_FILE}"
fi

# Run the ant script
xvfb-run java -ea -jar "${LAUNCHERJAR}" \
	-data "${WORKSPACE}" \
	-application org.eclipse.ant.core.antRunner \
	-buildfile "${ANT_FILE}" "${ANT_TARGET}"

# If there was an error, read the Eclipse logs and exit
if [[ $? -gt 0 ]]
then
	cat "${WORKSPACE}/.metadata/.log"
	exit 1
fi

# Otherwise, exit 0, meaning success!
exit 0
