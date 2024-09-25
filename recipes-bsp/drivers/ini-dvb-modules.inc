SUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE}"


PV = "${KV}+${SRCDATE}"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}

do_populate_sysroot() {
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf ${nonarch_base_libdir}/modules/${KV}/extra"
