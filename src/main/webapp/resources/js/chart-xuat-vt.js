const ctxBarEx = document.getElementById('my-chart--ex-port')?.getContext('2d');
if(ctxBarEx) {

    const chartData = JSON.parse(document.querySelector('#chart-ex-port').innerHTML);
    if(chartData.length === 0) {
        document.querySelector('.chart-container__ex-port').innerHTML = '<h5>Chưa có vật tư nào được nhập !</h5>';
    } else {

        const labels = chartData.map(data => data.supplyName);
        const dataset = chartData.map(data => data.quantity);

        let lenOfColorArr = borderColor.length;
        const numOfColor = lenOfColorArr - 1;
        while (lenOfColorArr < dataset.length) {
            borderColor.push(borderColor[lenOfColorArr % numOfColor])
            bgColor.push(bgColor[lenOfColorArr % numOfColor])
            lenOfColorArr++;
        }
        const data = {
            labels: labels,
            datasets: [{
                axis: 'y',
                label: 'Số lượng',
                data: dataset,
                fill: false,
                backgroundColor: bgColor,
                borderColor: borderColor,
                borderWidth: 1
            }]
        };
        const monthNow = ('0' + (new Date().getMonth() + 1)).slice(-2);
        new Chart(ctxBarEx, {
            type: 'bar',
            data: data,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    title: {
                        display: true,
                        text: `Top 10 số lượng xuất của vật tư trong tháng ${monthNow}`,
                        font: {
                            family: 'IBM Plex Sans',
                            size: 18,
                            weight: 'bold',
                            lineHeight: 1.2,
                        },
                    },
                    legend: {
                        display: false
                    }
                }
            },
        });
    }
}
