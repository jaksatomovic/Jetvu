import axios from 'axios';
import { Base64 } from 'js-base64';

function b64DecodeUnicode(str) {
    return Base64.decode(str)
}

export function tokenData() {
    let token = localStorage.getItem('accessToken')
    token = token ? token : "."
    let tokenParts = token.split('.')
    if (tokenParts.length !== 3) {
        return
    }
    let tokenData = JSON.parse(b64DecodeUnicode(tokenParts[1]))
    return tokenData
}

export function isLogged() {
    return tokenData() ? true : false
}

export function onLogout() {
    localStorage.clear()
    delete axios.defaults.headers.common['Authorization']
}