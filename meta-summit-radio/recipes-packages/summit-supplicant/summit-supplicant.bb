OPENSSLVER = "_openssl_3_0"

RDEPENDS:${PN} += "libssl (>= 3.0.0) libcrypto (>= 3.0.0)"

require summit-supplicant.inc radio-stack-su60-version.inc
