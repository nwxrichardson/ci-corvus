# Stage 1: download and run the installer
FROM eclipse-temurin:21 AS eclipse-installer

# See scripts/env-*.sh for values, and use scripts/local-build.sh for local build

ARG EPSILON_VERSION
ARG INSTALLABLE_UNITS

# Run eclipse P2 director to install an Epsilon setup for executing ant
# https://wiki.eclipse.org/Equinox/p2/Director_application
# https://help.eclipse.org/latest/index.jsp?topic=/org.eclipse.platform.doc.isv/guide/p2_director.html

# Command-line options for the eclipse platform binary:
# https://help.eclipse.org/latest/topic/org.eclipse.platform.doc.user/tasks/running_eclipse.htm

ARG ECLIPSE_OPERATING_SYSTEM="linux64"
ARG ECLIPSE_INSTALLER_ARCHIVE_FILE="eclipse-inst-${ECLIPSE_OPERATING_SYSTEM}.tar.gz"
ARG ECLIPSE_INSTALLER_DOWNLOAD_URL="https://www.eclipse.org/downloads/download.php?file=/oomph/products/${ECLIPSE_INSTALLER_ARCHIVE_FILE}&r=1"

RUN echo "Downloading from '${ECLIPSE_INSTALLER_DOWNLOAD_URL}'" \
    && wget --quiet --max-redirect=1 --output-document="${ECLIPSE_INSTALLER_ARCHIVE_FILE}" "${ECLIPSE_INSTALLER_DOWNLOAD_URL}" \
    && tar --extract --warning=no-unknown-keyword --file=${ECLIPSE_INSTALLER_ARCHIVE_FILE} \
    && rm ${ECLIPSE_INSTALLER_ARCHIVE_FILE}

# Specifications for the installation to be performed
# Repositories that the P2 director should use as sources to obtain the Installable Units (IUs)

ARG P2_REPOSITORIES=\
https://mirror.aarnet.edu.au/pub/eclipse/releases/latest,\
https://mirror.aarnet.edu.au/pub/eclipse/eclipse/updates/latest,\
https://mirror.aarnet.edu.au/pub/eclipse/epsilon/${EPSILON_VERSION},\
https://mirror.aarnet.edu.au/pub/eclipse/emfatic/update,\
https://mirror.aarnet.edu.au/pub/eclipse/modeling/gmp/gmf-tooling/updates/releases,\
https://mirror.aarnet.edu.au/pub/eclipse/birt/update-site/latest,\
https://download.eclipse.org/sirius/updates/nightly/latest/2023-03,\
https://committed-consulting.gitlab.io/mde-devops/eclipse-ant-contributions/

# # Start the install
# RUN ./eclipse-inst \
# # Do not show the splash screen
# -nosplash \
# # Run terminal log
# -consoleLog \
# # Select the director application
# -application org.eclipse.equinox.p2.director \
# # Use these repositories as source of installable units, and follow their upstream update site locations (if supplied)
# -repository ${P2_REPOSITORIES} \
# # Install these installable units
# -installIU ${INSTALLABLE_UNITS} \
# # Use this profile to install
# -profile SDKProfile \
# # Turn update on for this profile
# -profileProperties org.eclipse.update.install.features=true \
# # Install into this directory (must be same as in final image)
# -destination /epsilon

RUN eclipse-installer/eclipse-inst \
  -nosplash \
  -consoleLog \
  -application org.eclipse.equinox.p2.director \
  -repository ${P2_REPOSITORIES} \
  -installIU ${INSTALLABLE_UNITS} \
  -profile SDKProfile \
  -profileProperties org.eclipse.update.install.features=true \
  -destination /epsilon

FROM eclipse-temurin:21 AS runner

# Redeclare the OS argument for this stage
ARG ECLIPSE_OPERATING_SYSTEM

# Disable interactivity for install of git
ARG DEBIAN_FRONTEND=noninteractive

RUN echo "Running on OS '${ECLIPSE_OPERATING_SYSTEM}'" \
    && apt-get update \
    && apt-get install --yes --no-install-recommends \
         xvfb \
         dbus-x11 \
         firefox \
         libxtst6 \
         libxrender1 \
         file \
         wget \
         git \
         nano \
    && rm -rf /var/lib/apt/lists/*

# Copy over the entrypoint script
COPY /docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh && mkdir /workspace

# Copy over Eclipse installation
COPY --from=eclipse-installer /epsilon /epsilon

# set the entrypoint
ENTRYPOINT [ "/docker-entrypoint.sh" ]
