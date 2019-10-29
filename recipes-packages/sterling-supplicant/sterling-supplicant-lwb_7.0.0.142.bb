SUMMARY = "Laird Wi-Fi LWB Sterling Supplicant"

SRC_URI += "${LRD_LWB_URI_BASE}/sterling_supplicant-src-${PV}.tar.gz"

SRC_URI[md5sum] = "f1ad04f62719a7b14971c713f8edf8a8"
SRC_URI[sha256sum] = "b1ff34606a0259be448625ced21986fecdeccfb25474f3accecc7de03cd4a6da"

include sterling-supplicant.inc
