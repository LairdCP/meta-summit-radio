OPENSSLVER = "_openssl_1_0_2"

DEPENDS += "openssl10"

RDEPENDS_${PN} += "libcrypto (< 1.1.0)"

require adaptive-ww.inc ../radio-stack-summit-60-version.inc
