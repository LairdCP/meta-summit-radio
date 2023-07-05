SUMMARY = "Wi-Fi Summit Supplicant Libraries for LWB"

SRC_URI += "${SUMMIT_URI}/summit_supplicant_libs${SUPP_ARCH}-${PV}.tar.bz2;name=summit-supplicant-libs${SUPP_ARCH}"

require summit-supplicant-libs.inc radio-stack-lwb-version.inc
