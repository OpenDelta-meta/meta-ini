DESCIPTION = "S3 set eth0 wol"
MAINTAINER = "INI Team"
LICENSE = "CLOSED"
require conf/license/license-close.inc


SRC_URI = "file://ethwol.sh"

inherit update-rc.d
INITSCRIPT_NAME = "ethwol"
INITSCRIPT_PARAMS = "stop 32 0 ."

S = "${WORKDIR}"

FILES_${PN} = "${sysconfdir}"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/ethwol.sh ${D}${sysconfdir}/init.d/ethwol
}
