SUMMARY = "Summit Bluetooth 60 Auto World-Wide Mode"
SECTION = "Wireless"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pkgconfig update-rc.d systemd

DEPENDS = "libnl bluez5"

SUMMIT_URI ?= "${SUMMIT_URI_BASE}"
SUMMIT_URI_summit-internal = "${SUMMIT_URI_BASE}/adaptive_bt/src/${PV}"

SRC_URI = "${SUMMIT_URI}/adaptive_bt-src-${PV}.tar.gz;name=adaptive-bt"

S = "${WORKDIR}/adaptive_bt-${PV}"

EXTRA_OEMAKE = "'LINKOPT=${LDFLAGS}'"

SYSTEMD_SERVICE_${PN} = "adaptive_bt.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_NAME = "adaptive_bt"
INITSCRIPT_PARAMS = "defaults 30"

FILES_${PN} += "${sysconfdir}"

do_install () {
	install -D -t ${D}${bindir} -m 755 ${S}/bin/adaptive_bt
	install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/support/adaptive_bt.service
	install -D -t ${D}${sysconfdir}/init.d -m 755 ${S}/support/adaptive_bt
}