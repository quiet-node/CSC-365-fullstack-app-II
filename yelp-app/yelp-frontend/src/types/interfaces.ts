export interface IBusiness {
  id: number;
  business_id: string;
  name: string;
  address: string;
  stars: number;
  reviews: number;
  similarityRate: number;
  categories: Array<String>;
}

export interface ICentroid {
  id: number;
  businessName: string;
  categories: Array<String>;
}

export interface IBusinessList {
  bList: Array<IBusiness>;
}

export interface IBusinessCluster {
  centroid: Array<IBusiness>;
}
