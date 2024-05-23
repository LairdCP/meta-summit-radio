SUMMARY = "Wi-Fi Summit Supplicant Legacy Libraries"

require summit-supplicant-libs.inc radio-stack-msd-version.inc

SRC_URI = "${SUMMIT_URI}/summit_supplicant_libs_legacy${SUPP_ARCH}-${PV}.tar.bz2;name=summit-supplicant-libs-legacy${SUPP_ARCH}"

RDEPENDS:${PN} += "libnl libnl-genl libnl-route"

FILES:${PN}-dev += "${libdir}/libsdc_sdk.so"

do_install:append() {
	install -D -t ${D}${bindir} -m 0644 ${S}/usr/bin/*

	install -D -t ${D}${libdir} -m 0644 ${S}/usr/lib/libsdc_sdk.so.1*
	ln -rsf ${D}${libdir}/libsdc_sdk.so.1 ${D}/usr/lib/libsdc_sdk.so
	install -D -t ${D}${includedir} -m 0644 ${S}/usr/include/*
}

