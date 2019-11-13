SUMMARY = "Laird Wi-Fi LWB Sterling Supplicant"

SRC_URI += "${LRD_LWB_URI_BASE}/sterling_supplicant-src-${PV}.tar.gz"

SRC_URI[md5sum] = "9b40bd615660b55393a14e16d09b6450"
SRC_URI[sha256sum] = "7344ca652b3ef07985ae0d4b98599e2476d8640c9e7ca0db180a87f906c2a0dd"

include sterling-supplicant.inc
