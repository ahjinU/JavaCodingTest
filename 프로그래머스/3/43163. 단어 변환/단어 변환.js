function solution(begin, target, words) {
    
    let ind = 0;
    let visited = new Array(words.length).fill(0)
    let result = -1;
    bfs(0)
    console.log(result)
    if(result==-1) return 0;
    else return result
    function bfs(num){
        let arr = {}
        let count = 0;
        arr[ind] = [begin, count]
        
        while(Object.keys(arr).length){
            count++;
            let cur = arr[ind][0] //pop
            console.log(arr[ind])
            if(cur===target) {
                result = arr[ind][1];
                break;
            }
            delete arr[ind++]
            
            words.forEach((e,index)=>{
                if(visited[index]==0){
                    let cnt = 0
                    e = e.split("")
                    e.forEach((i, index)=>{
                        if(i !== cur[index]) cnt++;
                    })
                    if(cnt == 1) {
                        visited[index]=1;
                        arr[ind] = [e.join(""),count]; 
                    }
                }
              
            })
        }
    }
    
}

