import { Profile } from './dtos/Profile';

export interface User {
  profile: Profile;
  token: string;
}
