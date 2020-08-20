import axios from 'axios'
import { Message } from 'element-ui'

// 响应拦截
axios.interceptors.response.use(success=>{
    //Http状态码为200，业务逻辑错误返回错误码500
    if(success.status && success.status == 200 && success.data.status == 500){
        Message.error({message : success.data.msg});
        return;
    }
    return success.data;
},error=>{
    if(error.response.status == 504 || error.response.status == 404){
        Message.error({message : "服务器被吃了😶😶😶"});
    }else if(error.response.status == 403){
        Message.error({message : "权限不足,请联系系统管理员!"});    
    }else if(error.response.status == 401){
        Message.error({message : "尚未登录,请登录!"});
    }else{
        if(error.response.data.msg){
            Message.error({message : error.response.data.msg});
        }else{
            Message.error({message : "未知错误!"});
        }
    }
    return;
})

let base = "";

export const postKeyValueRequest = (url,param)=>{
    return axios({
        method : 'post',
        url : `${base}${url}`,
        data : param,
        transformRequest : [function(data){
            let ret = '';
            for(let i in data){
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&';
            }
            return ret;
        }],
        headers:{
            'Content-Type' : 'application/x-www-form-urlencoded'
        }
    });
}