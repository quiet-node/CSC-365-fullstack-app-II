import BusinessTable from '../table/BusinessTable';

interface Props {
  similarBs: any[];
}

const BusinessList = ({ similarBs }: Props): JSX.Element => {
  // console.log(allBs.length);
  return (
    <div className=' w-5/6 drop-shadow-xl '>
      <BusinessTable similarBs={similarBs} />
    </div>
  );
};

export default BusinessList;
