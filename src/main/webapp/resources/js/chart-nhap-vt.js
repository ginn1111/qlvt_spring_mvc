
const bgColor = [
    'rgba(255, 99, 132, 0.2)',
    'rgba(255, 159, 64, 0.2)',
    'rgba(255, 205, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(201, 203, 207, 0.2)'
]
const borderColor = [
    'rgb(255, 99, 132)',
    'rgb(255, 159, 64)',
    'rgb(255, 205, 86)',
    'rgb(75, 192, 192)',
    'rgb(54, 162, 235)',
    'rgb(153, 102, 255)',
    'rgb(201, 203, 207)'
]
const ctxBar = document.getElementById('my-chart--in-port')?.getContext('2d');
if(ctxBar) {

    const chartData = JSON.parse(document.querySelector('#chart-in-port').innerHTML);
    if(chartData.length === 0) {
       document.querySelector('.chart-container__in-port').innerHTML = '<h5>Chưa có vật tư nào được nhập !</h5>';
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
        const myChartBar = new Chart(ctxBar, {
            type: 'bar',
            data: data,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    title: {
                        display: true,
                        text: `Top 10 số lượng nhập của vật tư trong tháng ${monthNow}`,
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