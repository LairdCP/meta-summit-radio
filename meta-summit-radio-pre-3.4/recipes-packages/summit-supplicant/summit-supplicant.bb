OPENSSLVER = ""

RDEPENDS_${PN} += "libssl (>= 1.1.0) libcrypto (>= 1.1.0)"

require summit-supplicant.inc radio-stack-su60-version.inc
