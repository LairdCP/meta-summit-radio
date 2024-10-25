SUMMARY = "NX61x Firmware SDIO-UART"

require radio-firmware.inc

LICENSE += "& NXP2"
NO_GENERIC_LICENSE[NXP2] = "LICENSE.nxp2"
LIC_FILES_CHKSUM += "file://LICENSE.nxp2;md5=7b112d07b0616149941639c05f68f431"

require radio-stack-nx-version.inc

SRC_URI = "${SUMMIT_URI}/summit-${BPN}-${PV}.tar.bz2;name=${BPN}"
