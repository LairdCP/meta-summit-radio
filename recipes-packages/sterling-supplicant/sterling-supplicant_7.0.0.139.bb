SUMMARY = "Laird Wi-Fi 60 Sterling Supplicant"

SRC_URI += "${LRD_60_URI_BASE}/sterling_supplicant-src-${PV}.tar.gz"

SRC_URI[md5sum] = "15cbf213533075ea89aad4bfc9f62de6"
SRC_URI[sha256sum] = "533911f83579a6f5562774e7e2472cb787eb100e5dae447f7efe553692df1b0e"

include sterling-supplicant.inc
