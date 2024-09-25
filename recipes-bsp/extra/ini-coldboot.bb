SUMMARY = "S3 cold boot"
MAINTAINER = "ini Team"
LICENSE = "CLOSED"
require conf/license/license-close.inc


SRC_URI = "file://coldboot file://coldboot.sh"

inherit update-rc.d
INITSCRIPT_NAME = "coldboot"
INITSCRIPT_PARAMS = "start 30 0 ."

S = "${WORKDIR}"

FILES_${PN} = "${bindir} ${sysconfdir}"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/coldboot.sh ${D}${sysconfdir}/init.d/coldboot
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/coldboot ${D}${bindir}/coldboot
}
