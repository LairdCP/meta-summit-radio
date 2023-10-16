SUMMARY = "Summit Backports for QCACLD"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','qcacld','qcacld_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports \
        kernel-module-lwb-if-backports \
        kernel-module-msd45-backports \
        kernel-module-msd50-backports \
        kernel-module-nx-backports \
        "

require summit-backports.inc radio-stack-qcacld-version.inc
