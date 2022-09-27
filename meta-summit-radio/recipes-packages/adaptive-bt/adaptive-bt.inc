SUMMARY = "Summit Bluetooth SU60 Auto World-Wide Mode"
SECTION = "Wireless"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pkgconfig update-rc.d systemd

DEPENDS = "libnl bluez5"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:summit-internal = "https://files.devops.rfpros.com/builds/linux/adaptive_bt/src/${PV}"

SRC_URI = "${LRD_URI}/adaptive_bt-src-${PV}.tar.gz;name=adaptive-bt"

S = "${WORKDIR}/adaptive_bt-${PV}"

EXTRA_OEMAKE = "'LINKOPT=${LDFLAGS}'"

SYSTEMD_SERVICE:${PN} = "adaptive_bt.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_NAME = "adaptive_bt"
INITSCRIPT_PARAMS = "defaults 30"

FILES:${PN} += "${sysconfdir}"

do_install () {
	install -D -t ${D}${bindir} -m 755 ${S}/bin/adaptive_bt

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/support/adaptive_bt.service
	else
		install -D -t ${D}${sysconfdir}/init.d -m 755 ${S}/support/adaptive_bt
	fi
}