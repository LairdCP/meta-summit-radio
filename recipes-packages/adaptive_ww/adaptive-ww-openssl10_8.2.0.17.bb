include adaptive-ww.inc

OPENSSLVER = "_openssl_1_0_2"

DEPENDS += "openssl10"

RDEPENDS_${PN} += "libcrypto (< 1.1.0)"
