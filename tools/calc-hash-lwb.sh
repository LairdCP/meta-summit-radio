#! /bin/sh

[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

[ -z "${RFPROS_FILESHARE_USER}" ] || \
  RFPROS_FILESHARE_AUTH="${RFPROS_FILESHARE_USER}:${RFPROS_FILESHARE_PASS}@"

file="radio-stack-lwb-hashes.inc"
prefix="https://${RFPROS_FILESHARE_AUTH}files.devops.rfpros.com/builds/linux"
prefixext="https://github.com/LairdCP/Sterling-LWB-and-LWB5-Release-Packages/releases/download/LRD-REL-${ver}"

calc_file () {
  wget -t 1 -T 4 "${prefix}/${1}/${ver}/${2}" || wget -t 1 -T 4 "${prefixext}/${2}" || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum "${2}" | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum "${2}" | awk '{print $1}')\"" >> ${file}
  rm -f "${2}"
}

printf 'RADIO_VERSION = "%s"\n' "${ver}" > ${file}

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "summit-lwbplus-firmware-${ver}.tar.bz2" "lwbplus-firmware"

for i in etsi fcc jp
do
  calc_file "firmware" "summit-lwb-${i}-firmware-${ver}.tar.bz2" "lwb-${i}-firmware"
done

for i in etsi fcc ic jp
do
  calc_file "firmware" "summit-lwb5-${i}-firmware-${ver}.tar.bz2" "lwb5-${i}-firmware"
done

for i in sdio-div sdio-sa sdio-sa-m2 usb-div usb-sa usb-sa-m2
do
  calc_file "firmware" "summit-lwb5plus-${i}-firmware-${ver}.tar.bz2" "lwb5plus-${i}-firmware"
done

for i in sdio pcie
do
  calc_file "firmware" "summit-if573-${i}-firmware-${ver}.tar.bz2" "if573-${i}-firmware"
done

calc_file "firmware" "summit-if513-sdio-firmware-${ver}.tar.bz2" "if513-sdio-firmware"
