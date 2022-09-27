OPENSSLVER = "_legacy_openssl_1_0_2"

DEPENDS += "openssl10"

RDEPENDS_${PN} += "libssl (< 1.1.0) libcrypto (< 1.1.0)"

require summit-supplicant-legacy.inc radio-stack-4550-version.inc
