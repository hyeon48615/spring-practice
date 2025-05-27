const BASE_URL = `/api/v1/bbs/replies`;

async function getReplyList({bbs_idx, page_no, page_size, goLast}){
    const result = await axios.get(`${BASE_URL}/list/${bbs_idx}`, {params:{page_no, page_size}});

    if (goLast) {
        const total_count = result.data.total;
        const lastPage = parseInt(Math.ceil(total_count/page_size));
        return getReplyList({bbs_idx, lastPage, page_size});
    }
    console.log("reply list: " + JSON.stringify(result.data));
    return result.data;
}

async function getReply(idx) {
    const result = await axios.get(`${BASE_URL}/${idx}`);
    console.log("reply view: " + JSON.stringify(result.data));
    return result.data;
}

async function addReply(reply) {
    const result = await axios.post(`${BASE_URL}`, reply)
    console.log("reply regist: " + JSON.stringify(result.data));
    return result.data;
}

async function modifyReply(reply) {
    const result = await axios.put(`${BASE_URL}/${reply.idx}`, reply);
    console.log("reply modify: " + JSON.stringify(result.data));
    return result.data;
}

async function removeReply(idx) {
    const result = await axios.delete(`${BASE_URL}/${idx}`);
    console.log("reply delete: " + JSON.stringify(result.data));
    return result.data;
}