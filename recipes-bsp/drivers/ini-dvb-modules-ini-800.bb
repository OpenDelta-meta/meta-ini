require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(mbultra|atemionemesis)$"

SRCDATE = "20160121"

SRC_URI[md5sum] = "3919231c1ee6ad231c5bc75ffca37b2f"
SRC_URI[sha256sum] = "242028aa90ace81b62d50ca115801c5d276d8aba6291eb7544831aabaabb60c9"

SRC_URI = "http://source.mynonpublic.com/ini/ini-800-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/$i.ko
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
    
    install -d ${D}${sysconfdir}/modprobe.d
    echo "blacklist rtk_btusb" > ${D}${sysconfdir}/modprobe.d/blacklist.conf    
}
