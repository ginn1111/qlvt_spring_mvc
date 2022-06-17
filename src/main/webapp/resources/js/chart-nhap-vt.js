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
// let lenOfColorArr = borderColor.length;
// const numOfColor = lenOfColorArr - 1;
// while (lenOfColorArr < dataEmp.length) {
//     borderColor.push(borderColor[lenOfColorArr % numOfColor])
//     bgColor.push(bgColor[lenOfColorArr % numOfColor])
//     lenOfColorArr++;
// }
const ctxBar = document.getElementById('my-chart')?.getContext('2d');
const data = {
    labels: ['Xi măng', 'Gạch ngói', 'Cát trắng', 'Gạch 4 ống', 'Gạch men','Xi măng', 'Gạch ngói', 'Cát trắng', 'Gạch 4 ống', 'Gạch men'],
    datasets: [{
        axis: 'y',
        label: 'Số lượng',
        data: [...Array(10)].map(_ => Math.floor(Math.random() * 100) + 1),
        fill: false,
        backgroundColor: bgColor,
        borderColor: borderColor,
        borderWidth: 1
    }]
};
if(ctxBar) {

    const myChartBar = new Chart(ctxBar, {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                title: {
                    display: true,
                    text: 'Số lượng nhập của từng loại vật tư trong tháng 06',
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