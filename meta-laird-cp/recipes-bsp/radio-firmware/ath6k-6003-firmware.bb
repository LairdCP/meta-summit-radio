SUMMARY = "Laird Connectivity MSD45 Firmware SDIO-UART"

require radio-firmware.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://ath6kl.conf"

do_Install_append () {
        install -D -m 0644 ${S}/ath6kl.conf ${D}$(sysconfdir}/modprobe.d/ath6kl.conf
}

require radio-stack-4550-version.inc
