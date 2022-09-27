OPENSSLVER = "_openssl_3_0"

RDEPENDS:${PN} += "libcrypto (>= 3.0.0)"

require adaptive-ww.inc radio-stack-su60-version.inc
