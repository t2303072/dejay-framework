
/**
 * 태그 존재 여부 확인 존재하지 않으면 false 존재하면 true
 * @param arrayTag (Array)
 * @returns {boolean}
 */
function isExist(arrayTag){
    for(let i=0; i< arrayTag.length; i++){
        let htmlTag = document.getElementById(arrayTag[i]);
        if(isEmpty(htmlTag)) return false;
    }
    return true;
}

/**
 * 빈 값 여부 확인
 * @param targetData
 * @returns {boolean}
 */
function isEmpty(targetData){
    if(targetData === '' || targetData === null || targetData === undefined){
        return true;
    }
    return false;
}