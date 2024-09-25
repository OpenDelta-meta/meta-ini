require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(evoslim)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "a0bbd2b0a26d750e89972364e52fa4e9"
SRC_URI[sha256sum] = "80997180ab4092afbbefe1e42914ff1c66fe9de861789e9c4b86027dbddb840e"

SRC_URI = "http://source.mynonpublic.com/ini/yhgd5034-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/$i.ko
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
