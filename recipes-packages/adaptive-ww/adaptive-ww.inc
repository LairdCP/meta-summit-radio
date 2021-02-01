SUMMARY = "Laird Connectivity Wi-Fi Summit 60 Auto World-Wide Mode"
SECTION = "Wireless"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit lrd-url

SYSTEMD_SERVICE_${PN} = "adaptive_ww.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_NAME_${PN} = "adaptive_ww"
INITSCRIPT_PARAMS_${PN} = "defaults 30"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

SRC_URI_append_x86 = " ${LRD_URI_LOCAL}/adaptive_ww${OPENSSLVER}-x86-${PV}.tar.bz2;subdir=src "
SRC_URI_append_x86-64 = " ${LRD_URI_LOCAL}/adaptive_ww${OPENSSLVER}-x86-64-${PV}.tar.bz2;subdir=src "
SRC_URI_append_powerpc64 = " ${LRD_URI_LOCAL}/adaptive_ww${OPENSSLVER}-powerpc-e5500-${PV}.tar.bz2;subdir=src "
SRC_URI_append_arm = " ${LRD_URI_LOCAL}/adaptive_ww${OPENSSLVER}-arm-${HFP}-${PV}.tar.bz2;subdir=src "
SRC_URI_append_aarch64 = " ${LRD_URI_LOCAL}/adaptive_ww${OPENSSLVER}-aarch64-${PV}.tar.bz2;subdir=src "

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS_${PN} = "libnl libnl-genl libnl-route libcrypto"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

ALLOW_EMPTY_${PN}-dev = "0"
ALLOW_EMPTY_${PN}-dbg = "0"

FILES_${PN} += "${base_libdir}/* ${sysconfdir}/*"

do_install () {
	install -D -t ${D}${bindir} -m 755 ${S}/usr/bin/adaptive_ww
	install -D -t ${D}${base_libdir}/firmware/lrdmwl -m 644 ${S}/lib/firmware/lrdmwl/regpwr.db

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/usr/lib/systemd/system/adaptive_ww.service
	else
		install -D -t ${D}${sysconfdir}/init.d -m 755 ${S}/etc/init.d/adaptive_ww
	fi
}
