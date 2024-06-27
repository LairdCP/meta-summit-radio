#! /bin/sh

[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

[ -z "${RFPROS_FILESHARE_USER}" ] || \
  RFPROS_FILESHARE_AUTH="${RFPROS_FILESHARE_USER}:${RFPROS_FILESHARE_PASS}@"

file="radio-stack-60-hashes.inc"
prefix="https://${RFPROS_FILESHARE_AUTH}files.devops.rfpros.com/builds/linux"
prefixext="https://github.com/LairdCP/Sterling-60-Release-Packages/releases/download/LRD-REL-${ver}"

calc_file () {
  wget -t 1 -T 4 "${prefix}/${1}/${ver}/${2}" || wget -t 1 -T 4 "${prefixext}/${2}" || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum "${2}" | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum "${2}" | awk '{print $1}')\"" >> ${file}
  rm -f "${2}"
}

printf 'RADIO_VERSION = "%s"\n' "${ver}" > ${file}

for i in x86 x86_64 arm-eabi arm-eabihf aarch64 powerpc64-e5500
do
  calc_file "adaptive_ww/laird" "adaptive_ww-${i}-${ver}.tar.bz2" "adaptive-ww-${i}"
  calc_file "summit_supplicant/laird" "summit_supplicant_libs-${i}-${ver}.tar.bz2" "summit-supplicant-libs-${i}"
done

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "adaptive_bt/src" "adaptive_bt-src-${ver}.tar.gz" "adaptive-bt"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"

for i in pcie-uart pcie-usb sdio-uart sdio-sdio usb-usb usb-uart
do
  calc_file "firmware" "summit-60-radio-firmware-${i}-${ver}.tar.bz2" "60-radio-firmware-${i}"
done

calc_file "firmware" "summit-som8mp-radio-firmware-${ver}.tar.bz2" "som8mp-radio-firmware"
