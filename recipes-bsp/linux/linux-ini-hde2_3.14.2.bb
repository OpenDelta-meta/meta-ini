SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(opticumtt|mbminiplus|mbhybrid|evoslim|bwidowx2|beyonwizt2|atemio6200|atemio6100|atemio6000)$"

SRCDATE = "20150605"

SRC_URI[md5sum] = "166ae419fe710ca8487be6cb6ef83b77"
SRC_URI[sha256sum] = "f8c54d44f3218809d3b980911075c1094619536bb2723367cc8eacf3665b6df2"

inherit kernel machine_kernel_pr samba_change_dialect

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_${KERNEL_PACKAGE_NAME}-base = "${KERNEL_PACKAGE_NAME}-base"
PKG_${KERNEL_PACKAGE_NAME}-image = "${KERNEL_PACKAGE_NAME}-image"
RPROVIDES_${KERNEL_PACKAGE_NAME}-base = "${KERNEL_PACKAGE_NAME}-${KERNEL_VERSION}"
RPROVIDES_${KERNEL_PACKAGE_NAME}-image = "${KERNEL_PACKAGE_NAME}-image-${KERNEL_VERSION}"

SRC_URI = "http://source.mynonpublic.com/ini/bcm7362-linux-${KV}-${SRCDATE}.tgz \
	file://defconfig \
	file://${OPENVISION_BASE}/meta-openvision/recipes-linux/kernel-patches/kernel-add-support-for-gcc${VISIONGCCVERSION}.patch \
	file://add-rt2x00-wifi-devices.patch \
	file://add-rtl8192cu-wifi-devices.patch \
	file://add-dmx-source-timecode.patch \
	file://af9015-output-full-range-SNR.patch \
	file://af9033-output-full-range-SNR.patch \
	file://cxd2820r-output-full-range-SNR.patch \
	file://dvb_usb_disable_rc_polling.patch \
	file://dvb-usb-dib0700-disable-sleep.patch \
	file://fix-proc-cputype.patch \
	file://iosched-slice_idle-1.patch \
	file://it913x-switch-off-PID-filter-by-default.patch \
	file://mxl5007t-add-no_probe-and-no_reset-parameters.patch \
	file://tda18271-advertise-supported-delsys.patch \
	file://rt2800usb_fix_warn_tx_status_timeout_to_dbg.patch \
	file://linux-3.14.2-gcc-4.9.3-build-error-fixed.patch \
	file://rtl8712-fix-warnings.patch \
	file://0001-Support-TBS-USB-drivers.patch \
	file://0001-STV-Add-PLS-support.patch \
	file://0001-STV-Add-SNR-Signal-report-parameters.patch \
	file://0001-stv090x-optimized-TS-sync-control.patch \
	file://blindscan2.patch \
	file://genksyms_fix_typeof_handling.patch \
	file://0002-log2-give-up-on-gcc-constant-optimizations.patch \
	file://0003-cp1emu-do-not-use-bools-for-arithmetic.patch  \
	file://0004-makefile-disable-warnings.patch \
	"

S = "${WORKDIR}/linux-${PV}"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

FILES_${KERNEL_PACKAGE_NAME}-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

kernel_do_install_append() {
	${STRIP} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
	gzip -9c ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} > ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	rm ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
}

pkg_postinst_${KERNEL_PACKAGE_NAME}-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
			flash_erase /dev/${MTD_KERNEL} 0 0
			nandwrite -p /dev/${MTD_KERNEL} /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
			rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
		fi
	fi
	true
}

do_rm_work() {
}

# extra tasks
addtask kernel_link_images after do_compile before do_install

