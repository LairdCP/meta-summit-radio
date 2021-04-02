include radio-firmware.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RPROVIDES_${PN}  += "wireless-regdb-static"
RREPLACES_${PN}  += "wireless-regdb-static"
RCONFLICTS_${PN} += "wireless-regdb-static"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
