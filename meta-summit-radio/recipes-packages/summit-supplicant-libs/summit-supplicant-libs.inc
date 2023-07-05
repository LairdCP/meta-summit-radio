SECTION = "Wireless"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

SUMMIT_URI ?= "${SUMMIT_URI_BASE}"
SUMMIT_URI:summit-internal = "${SUMMIT_URI_BASE}/summit_supplicant/laird/${PV}"

SUPP_ARCH:x86 = "-x86"
SUPP_ARCH:x86-64 = "-x86_64"
SUPP_ARCH:powerpc64 = "-powerpc64-e5500"
SUPP_ARCH:arm = "-arm-${HFP}"
SUPP_ARCH:aarch64 = "-aarch64"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}"

INSANE_SKIP:${PN} = "ldflags already-stripped"
INSANE_SKIP:${PN}-passphrase = "ldflags already-stripped"
INSANE_SKIP:${PN}-cli = "ldflags already-stripped"

ALLOW_EMPTY:${PN}-dev = "0"
ALLOW_EMPTY:${PN}-dbg = "0"

FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/libsdcsupp.so"

do_install () {
	install -D -t ${D}${libdir} -m 0644 ${S}/usr/lib/libsdcsupp.so
}
