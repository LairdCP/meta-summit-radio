SUMMARY = "Sona NX61x Firmware SDIO-UART"

require radio-firmware.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require radio-stack-nx-version.inc

SRC_URI = "${SUMMIT_URI}/${BPN}-${PV}.tar.bz2;name=${BPN}"
