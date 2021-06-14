SUMMARY = "Laird Connectivity Bluetooth Summit 60 Auto World-Wide Mode"
SECTION = "Wireless"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pkgconfig lrd-url update-rc.d systemd

DEPENDS = "libnl bluez5"

SRC_URI = "\
	${LRD_URI_LOCAL}/adaptive_bt-src-${PV}.tar.gz \
	file://0001-adaptive_bt-Fixed-compilation-issues-with-newer-gcc-.patch \
	"

SRC_URI[md5sum] = "7b05f33a86423418e29531246b693a4b"
SRC_URI[sha256sum] = "00cc823250b3b1d12b4b733b01e3d539ba0bfe4d33063747008ee0874afb6ab7"

S = "${WORKDIR}/adaptive_bt-${PV}"

EXTRA_OEMAKE = "'LINKOPT=${LDFLAGS}'"

SYSTEMD_SERVICE_${PN} = "adaptive_bt.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_NAME = "adaptive_bt"
INITSCRIPT_PARAMS = "defaults 30"

FILES_${PN} += "${sysconfdir}"

do_install () {
	install -D -t ${D}${bindir} -m 755 ${S}/bin/adaptive_bt

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/support/adaptive_bt.service
	else
		install -D -t ${D}${sysconfdir}/init.d -m 755 ${S}/support/adaptive_bt
	fi
}