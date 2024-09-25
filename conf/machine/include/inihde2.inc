require conf/machine/include/ini-oem.inc

KERNELVERSION = "3.14.2"

MTD_KERNEL = "mtd2"
MTD_ROOTFS = "mtd0"

PREFERRED_PROVIDER_virtual/kernel = "linux-ini-hde2"
PREFERRED_VERSION_linux-ini-hde2 = "${KERNELVERSION}"

MACHINE_FEATURES += "videoenhancement extrasplash blindscan-tbs YUV"

SOC_FAMILY = "bcm7362"

MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	ini-cfe \
	"

STB_PLATFORM = "inihde2"
