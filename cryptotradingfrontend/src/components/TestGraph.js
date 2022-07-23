// import {
//     LineChart,
//     ResponsiveContainer,
//     Legend, Tooltip,
//     Line,
//     XAxis,
//     YAxis,
//     CartesianGrid
// } from 'recharts';
  
// // Sample chart data
// const pdata = [
//     {
//         name: 'MongoDb',
//         student: 11,
//     },
//     {
//         name: 'Javascript',
//         student: 15,
//     },
//     {
//         name: 'PHP',
//         student: 5,
//     },
//     {
//         name: 'Java',
//         student: 10,
//     },
//     {
//         name: 'C#',
//         student: 9,
//     },
//     {
//         name: 'C++',
//         student: 10,
//     },
// ];
  
// function TestGraph() {
//     return (
//         <>
//             <h1 className="text-heading">
//                 Crypto Real Time 
//             </h1>
//             <ResponsiveContainer width="100%" aspect={3}>
//                 <LineChart data={pdata} margin={{ right: 300 }}>
//                     <CartesianGrid />
//                     <XAxis dataKey="name" 
//                         interval={'preserveStartEnd'} />
//                     <YAxis></YAxis>
//                     <Legend />
//                     <Tooltip />
//                     <Line dataKey="student"
//                         stroke="black" activeDot={{ r: 8 }} />

//                 </LineChart>
//             </ResponsiveContainer>
//         </>
//     );
// }
  
// export default TestGraph;