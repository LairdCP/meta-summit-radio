SUMMARY = "Summit Backports for Mediatek"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','mt320','mt320_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports \
        kernel-module-lwb-if-backports \
        kernel-module-msd45-backports \
        kernel-module-msd50-backports \
        kernel-module-qcacld-backports \
        "

require summit-backports.inc radio-stack-mt-version.inc
