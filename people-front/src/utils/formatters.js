export function formatPhone(value) {
    value = value
        .replace(/\D/g, '')
        .slice(0, 11)

    if (value.length <= 10) {
        return value.replace(
            /(\d{2})(\d{4})(\d{0,4})/,
            '($1) $2-$3'
        )
    }

    return value.replace(
        /(\d{2})(\d{5})(\d{0,4})/,
        '($1) $2-$3'
    )

}

export function formatDocument(value) {
    value = value
        .replace(/\D/g, '')
        .slice(0, 14)

    // CPF
    if (value.length <= 11) {
        return value
            .replace(/(\d{3})(\d)/, '$1.$2')
            .replace(/(\d{3})(\d)/, '$1.$2')
            .replace(/(\d{3})(\d{1,2})$/, '$1-$2')
    }

    // CNPJ
    return value
        .replace(/^(\d{2})(\d)/, '$1.$2')
        .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
        .replace(/\.(\d{3})(\d)/, '.$1/$2')
        .replace(/(\d{4})(\d)/, '$1-$2')
}