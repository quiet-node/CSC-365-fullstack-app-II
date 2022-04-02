import { Link } from 'react-router-dom';
import Header from '../components/Header';

const Clusters = () => {
  return (
    <div className=' bg-slate-200 min-h-screen max-h-screen'>
      <div className='flex w-full justify-center flex-col items-center'>
        <div className='w-full mb-8 '>
          <Header />
        </div>
        <div className='flex justify-center items-center w-full'>
          <div className='bg-white h-24 flex justify-center items-center w-[750px] rounded-lg drop-shadow-2xl'>
            <button className='cursor-pointer bg-indigo-500 px-10 ml-5 shadow-2xl hover:drop-shadow-lg rounded-xl py-2 font-bold text-white hover:bg-indigo-600 '>
              Fetch Clusters
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Clusters;
