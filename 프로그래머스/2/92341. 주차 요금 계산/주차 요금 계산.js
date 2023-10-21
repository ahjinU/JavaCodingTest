function solution(fees, records) {
    let bt, bf, ut, uf; //기본 시간, 기본 요금, 단위 시간, 단위 요금
    [bt, bf, ut, uf] = fees
    let arr = {}, times = {}
    let result = []
    
    //시간부터 계산 해야됨
    records.forEach((e)=>{
        let time, num, cmd;
        [time,num,cmd] = e.split(" ");
        let h, m;
        [h,m] = time.split(":");
        time = h*60+m*1;
        if(cmd==="IN"){
            arr[num] = time;
        }else{
            time = time - arr[num];
            times[num]? times[num] +=time: times[num] = time;
            delete arr[num];
        }
    })
    
    for(let remain in arr){
        let time = 23*60+59-arr[remain];
        times[remain]? times[remain] +=time: times[remain] = time;
    }
    
    for(let e in times){
        let time = times[e]
        if(time > bt){ //기본보다 크면
            time -= bt;
            time = Math.ceil(time/ut)*uf
        }
        else time = 0;
        times[e] = bf + time;
    }
    
    let sortKey = Object.keys(times).sort()
    sortKey.forEach((e)=>{
        result.push(times[e])
    })
    
    return result
}