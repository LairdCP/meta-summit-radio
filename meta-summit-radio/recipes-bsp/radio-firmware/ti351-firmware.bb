SUMMARY = "TI351 Firmware"

require radio-firmware.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require radio-stack-ti-version.inc

TI351_REGDOMAIN ?= "WW"

SRC_URI = "${SUMMIT_URI}/laird-ti351-${TI351_REGDOMAIN}-firmware-${PV}.tar.bz2;name=${BPN}"
